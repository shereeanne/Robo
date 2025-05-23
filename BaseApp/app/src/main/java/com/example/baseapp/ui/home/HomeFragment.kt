package com.example.baseapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.baseapp.databinding.FragmentHomeBinding

import android.widget.FrameLayout
import org.qtproject.example.CarRobotApp.CarRobotContent.Screen01
import org.qtproject.qt.android.QtQuickView
import org.qtproject.qt.android.QtQuickViewContent
import org.qtproject.qt.android.QtQmlStatus
import org.qtproject.qt.android.QtQmlStatusChangeListener
import android.util.Log



class HomeFragment : Fragment() , QtQmlStatusChangeListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var firstQmlContent: Screen01 = Screen01()
    private var firstQtQuickView: QtQuickView? = null
    private val animationList = listOf("transform_to_vehicle_hero", "transform_to_robot_hero_timeline", "transform_to_vehicle_timeline", "transform_to_robot_timeline")
    private var animationCounter = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        firstQtQuickView = QtQuickView(this.activity)
        val params: ViewGroup.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.firstQmlFrame.addView(firstQtQuickView, params)
        firstQtQuickView?.loadContent(firstQmlContent)
        firstQmlContent.setStatusChangeListener(this)

        binding.buttonOne.setOnClickListener {
            animationCounter++
            firstQtQuickView?.setProperty("currentState", animationList[animationCounter % animationList.size])
        }

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onStatusChanged(status: QtQmlStatus?, content: QtQuickViewContent?) {

            firstQmlContent.connectIsPlayingChangeListener{ _:String, value: Boolean? ->
                if (firstQtQuickView!!.getProperty("isPlaying"))
                {
                    binding.buttonOne.text = "Playing...."
                }
                else
                    binding.buttonOne.text = "Cycle Animations"
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}