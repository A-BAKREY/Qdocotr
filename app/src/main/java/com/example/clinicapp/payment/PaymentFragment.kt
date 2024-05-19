package com.example.clinicapp.payment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import com.example.clinicapp.R
import com.example.clinicapp.booking.Booking
import com.example.clinicapp.databinding.DailogBinding
import com.example.clinicapp.databinding.PaymentBinding
import com.stripe.android.ApiResultCallback
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.DelicateCardDetailsApi
import com.stripe.android.model.Token
import com.stripe.android.view.CardInputWidget

class PaymentFragment : Fragment() {

    private var _binding: PaymentBinding? = null

    private lateinit var stripe: Stripe

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaymentBinding.inflate(inflater, container, false)
        val view = binding.root

        PaymentConfiguration.init(requireContext(), "pk_test_51PH0KKI5wBnZuduU2zCuk1a6xcTTJGDgTpkWx3Eobpd2HmmULXW24vNs5m3ypcXkEMHjzbNbMkNSpywVR1IaABF900PkGrSAr7")


        // إنشاء كائن Stripe
        stripe = Stripe(requireContext(), PaymentConfiguration.getInstance(requireContext()).publishableKey)

        // استدعاء دالة لتحديد العمل عند الضغط على زر الدفع
        setupPaymentButton()

        return view
    }
    @OptIn(DelicateCardDetailsApi::class)
    private fun setupPaymentButton() {

        binding.cancel.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Booking())
                .addToBackStack(null)
                .commit()
        }

        binding.submit.setOnClickListener {
            val cardInputWidget = binding.cardNumber
            val cardParams = cardInputWidget.cardParams
            if (cardParams?.number.isNullOrEmpty()){
                Toast.makeText(requireContext(),"يرجي ادخال رقم الكارت",Toast.LENGTH_LONG).show()
            }else{
                // Create a Token parameter to send to the server
                val stripe = Stripe(requireContext(), PaymentConfiguration.getInstance(requireContext()).publishableKey)
                stripe.createCardToken(
                    cardParams!!,
                    null,
                    null,
                    object : ApiResultCallback<Token> {
                        override fun onSuccess(result: Token) {
                            val tokenId = result.id
                            // Handle result here
                            successDialog(R.drawable.circle,"تم حجز الكشف بنجاح")
                            cardInputWidget.clear()
                        }

                        override fun onError(e: Exception) {
                            // Handle error
                            Toast.makeText(
                                requireContext(),
                                "Error: ${e.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
            }
            // Use Stripe SDK to collect card data

        }
    }
    private fun successDialog(image: Int,text:String) {

        val dialog = Dialog(requireContext())
        val binding = DailogBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        val window: Window? = dialog.window
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        binding.dialogImage.setImageResource(image)
        binding.dialogText.text = text
        dialog.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}