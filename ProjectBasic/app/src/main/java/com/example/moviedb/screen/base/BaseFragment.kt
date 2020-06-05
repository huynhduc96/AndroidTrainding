package com.example.moviedb.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.databinding.ViewDataBinding
import com.example.moviedb.BR


abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int
    private lateinit var viewDataBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    abstract fun initComponents(view: ViewBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding?.let { initComponents(it) }
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
