package flow.use.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.code3.R
import com.example.code3.databinding.ActivityMain5Binding
import flow.use2.viewModel.APIViewModel

// 协程+Retrofit+ViewModel+LiveData+DataBinding网络通信
// 协程配合上面组件，完成网络加载，展示多组件中协程有点
class MainActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 绑定DataBinding 即可
        val binding = DataBindingUtil.setContentView<ActivityMain5Binding>(this, R.layout.activity_main5)
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            APIViewModel::class.java)
        binding.vm = viewModel

        binding.bt.setOnClickListener {
            viewModel.requestLogin("Derry-vip", "123456")
        }
    }
}
