package com.example.clinicapp.AiSection

import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import java.io.FileInputStream
import java.io.IOException

class MyModel(private val assetManager: AssetManager) {
    private lateinit var interpreter: Interpreter

    init {
        val model = loadModelFile(assetManager, "model.tflite")
        interpreter = Interpreter(model)
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor: AssetFileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun runInference(input: FloatArray): FloatArray {
        val output = FloatArray(OUTPUT_SIZE)
        interpreter.run(input, output)
        return output
    }

    companion object {
        private const val OUTPUT_SIZE = 10 // ضع الحجم المناسب للإخراج هنا
    }
}