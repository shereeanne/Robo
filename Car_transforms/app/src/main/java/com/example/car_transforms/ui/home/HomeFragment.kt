package com.example.car_transforms.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.collection.emptyLongSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.car_transforms.databinding.FragmentHomeBinding

import org.qtproject.example.F1TransformerApp.F1TransformerContent.F1TransformerView
import org.qtproject.qt.android.QtQuickView
import org.qtproject.qt.android.QtQmlStatus
import org.qtproject.qt.android.QtQuickViewContent
import org.qtproject.qt.android.QtQmlStatusChangeListener


class HomeFragment : Fragment(), QtQmlStatusChangeListener{

    private var _binding: FragmentHomeBinding? = null

    private var animationTrigger = 0


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val firstQmlContent: F1TransformerView = F1TransformerView()

        val firstQtQuickView = QtQuickView(this.activity)
        val params: ViewGroup.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        firstQmlContent.setStatusChangeListener(this)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root
        binding.firstQmlFrame.addView(firstQtQuickView, params)

        binding.buttonOne.setOnClickListener {
            animationTrigger++
            firstQtQuickView.setProperty("animationTrigger", animationTrigger)

            val result = firstQtQuickView.getProperty<Boolean>("isPlaying")
            Log.e("Test", "result: $result")

            //This would need to  "poll" for changes and no in the button handler
            if (firstQtQuickView.getProperty("isPlaying"))
            {
                binding.buttonOne.text = "Playing...."
            }
            else
                binding.buttonOne.text = "Cycle Animations"

            if (animationTrigger > 10)
                animationTrigger = 0
        }

        firstQtQuickView.loadContent(firstQmlContent)

        return root
    }

    override fun onStatusChanged(status: QtQmlStatus?, content: QtQuickViewContent?) {
        Log.i("Test", "Status of QtQuickView: $status")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}