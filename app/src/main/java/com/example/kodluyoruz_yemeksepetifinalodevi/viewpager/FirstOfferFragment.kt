package com.example.kodluyoruz_yemeksepetifinalodevi.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kodluyoruz_yemeksepetifinalodevi.R


class FirstOfferFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.first_offer_fragment, container, false)
    }

}