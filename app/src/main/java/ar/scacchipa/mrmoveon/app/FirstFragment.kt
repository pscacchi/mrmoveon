package ar.scacchipa.mrmoveon.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ar.scacchipa.mrmoveon.R
import ar.scacchipa.mrmoveon.databinding.FragmentFirstBinding
import ar.scacchipa.mrmoveon.viewmodel.BoardViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.PI

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val boardVM: BoardViewModel by viewModel()
    private var ticker: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        ticker = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                boardVM.moveAll()
                delay(150)
            }
        }

        _binding?.boardView?.boardViewModel = boardVM

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonFirst?.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        _binding?.root?.setOnTouchListener { view,  motionEvent ->
            val xRel = motionEvent.rawX / view.width
            val yRel = motionEvent.rawY / view.height
            var degree = when {
                xRel > 0.66 -> {
                    when {
                        yRel > 0.66 -> 45.0
                        yRel > 0.33 -> 0.0
                        else -> 315.0
                    }
                }
                xRel > 0.33 -> {
                    when {
                            yRel > 0.66 -> 90.0
                            yRel > 0.33 -> null
                            else -> 270.0
                    }
                }
                else -> {
                    when {
                        yRel > 0.66 -> 135.0
                        yRel > 0.33 -> 180.0
                        else -> 225.0
                    }
                }
            }
            if (degree == null) {
                boardVM.removeDirection()
            } else {
                boardVM.setDireccion(PI * degree / 180)
            }
            true
        }

        boardVM.addObserver(this) {
            _binding!!.boardView.invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        ticker?.cancel()
        ticker = null
    }
}