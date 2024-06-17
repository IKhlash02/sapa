package com.example.sapa.ui.component

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun SoundPlayer(soundResId: Int, context: Context = LocalContext.current) {
    val audioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    val soundId = remember {
        soundPool.load(context, soundResId, 1)
    }

    // Memainkan suara saat Composable pertama kali muncul
    LaunchedEffect(soundPool, soundId) {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    // Membersihkan dan menghentikan suara saat Composable dibuang
    DisposableEffect(soundPool) {
        onDispose {
            soundPool.stop(soundId)
            soundPool.release()
        }
    }
}