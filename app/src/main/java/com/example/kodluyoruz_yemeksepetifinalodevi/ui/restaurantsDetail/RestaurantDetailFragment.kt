package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.RestaurantDetailFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IMealClickListener
import com.example.kodluyoruz_yemeksepetifinalodevi.ui.meals.MealListAdapter
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private lateinit var _binding: RestaurantDetailFragmentBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

    private val mealListAdapter = MealListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantDetailFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getRestaurantDetail(args.id!!).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("Restaurant List", "${it.data}")

                    val restaurant = it.data!!.data
                    val options = RequestOptions().placeholder(R.mipmap.ic_launcher)
                    Glide.with(_binding.imageViewRestaurant.context)
                        .applyDefaultRequestOptions(options)
                        .load(restaurant.image).into(_binding.imageViewRestaurant)
                    _binding.textViewRestaurantName.text = restaurant.name
                    _binding.textViewRestaurantAddress.text = restaurant.district
                    mealListAdapter.setMeals(restaurant.meals)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                    Log.v("Restaurant List", "${it.data}")
                }
            }
        })
    }

    private fun initViews() {
        _binding.mealsRecyclerView.adapter = mealListAdapter
        _binding.mealsRecyclerView.layoutManager = LinearLayoutManager(context)

        mealListAdapter.setMealOnClickListener(object : IMealClickListener {
            override fun onClick(name: MealsItem) {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(
                        name.id,
                        args.id.toString()
                    )
                findNavController().navigate(action)


            }
        })
    }


}