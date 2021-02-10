package com.fynzero.eggstimer.ui

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fynzero.eggstimer.R
import com.fynzero.eggstimer.viewmodel.TimerViewModel
import kotlinx.android.synthetic.main.fragment_soft.*

class SoftFragment : Fragment() {

    private lateinit var timerViewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soft, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerViewModel = ViewModelProviders.of(requireActivity())
            .get(TimerViewModel::class.java)

        timerViewModel.softTimer().observe(requireActivity(),
            Observer {
                txt_timer_soft.text = DateUtils.formatElapsedTime(it)
            })

        timerViewModel.softFinished.observe(requireActivity(),
            Observer {
                if (it) {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.finish),
                        Toast.LENGTH_SHORT
                    ).show()
                    img_egg.setImageResource(R.drawable.chick)
                    txt_enjoy_soft.visibility = View.VISIBLE
                    btn_start_soft.text = resources.getString(R.string.reset)
                }
            })

        var btnStatus = false
        setBtnStatus(btnStatus)
        btn_start_soft.setOnClickListener {
            if (!btnStatus) {
                timerViewModel.startSoftTimer()
                btnStatus = !btnStatus
                setBtnStatus(btnStatus)
            } else {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog.setTitle(resources.getString(R.string.alert_title))
                alertDialog
                    .setMessage(resources.getString(R.string.alert_message))
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, id ->
                        timerViewModel.stopSoftTimer()
                        btnStatus = !btnStatus
                        setBtnStatus(btnStatus)
                        txt_timer_soft.text = resources.getString(R.string.timer_soft)
                    }
                    .setNegativeButton("No") { dialog, id -> dialog.cancel() }
                alertDialog.create().show()
            }
        }
    }

    private fun setBtnStatus(state: Boolean) {
        if (state) {
            btn_start_soft.text = resources.getString(R.string.stop)
            img_egg.setImageResource(R.drawable.cooking)
            txt_enjoy_soft.visibility = View.INVISIBLE
        } else {
            btn_start_soft.text = resources.getString(R.string.start)
            img_egg.setImageResource(R.drawable.egg)
            txt_enjoy_soft.visibility = View.INVISIBLE
        }
    }
}