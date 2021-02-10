package com.fynzero.eggstimer.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val soft = MutableLiveData<Long>()
    private val medium = MutableLiveData<Long>()
    private val hard = MutableLiveData<Long>()

    var softFinished = MutableLiveData<Boolean>()
    var mediumFinished = MutableLiveData<Boolean>()
    var hardFinished = MutableLiveData<Boolean>()
    private lateinit var countDownTimer: CountDownTimer

    // soft timer
    fun startSoftTimer() {
        countDownTimer = object : CountDownTimer(220000, 1000) {
            override fun onFinish() {
                softFinished.value = true
            }

            override fun onTick(p0: Long) {
                soft.value = p0 / 1000
            }
        }.start()
    }

    fun stopSoftTimer() {
        countDownTimer.cancel()
    }

    fun softTimer(): LiveData<Long> = soft

    // medium timer
    fun startMediumTimer() {
        countDownTimer = object : CountDownTimer(280000, 1000) {
            override fun onFinish() {
                mediumFinished.value = true
            }

            override fun onTick(p0: Long) {
                medium.value = p0 / 1000
            }
        }.start()
    }

    fun stopMediumTimer() {
        countDownTimer.cancel()
    }

    fun mediumTimer(): LiveData<Long> = medium

    // hard timer
    fun startHardTimer() {
        countDownTimer = object : CountDownTimer(520000, 1000) {
            override fun onFinish() {
                hardFinished.value = true
            }

            override fun onTick(p0: Long) {
                hard.value = p0 / 1000
            }
        }.start()
    }

    fun stopHardTimer() {
        countDownTimer.cancel()
    }

    fun hardTimer(): LiveData<Long> = hard
}