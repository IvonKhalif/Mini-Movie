package com.example.minimovie.features.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minimovie.R
import com.example.minimovie.databinding.ItemReviewBinding
import com.example.minimovie.repository.review.ResultReviews

class ReviewAdapter(
    private val reviewList: MutableList<ResultReviews>
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    fun addReviewList(genres: List<ResultReviews>?) {
        genres?.let { reviewList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = reviewList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultReviews: ResultReviews) {
            binding.reviewModel = resultReviews
            binding.executePendingBindings()

            val lengthContent = resultReviews.content?.length ?: 0
            if (lengthContent < 40) {
                binding.textSeeMore.visibility = View.GONE
            } else {
                binding.textSeeMore.visibility = View.VISIBLE
            }

            var isMaxLine = binding.textReview.maxLines == Integer.MAX_VALUE
            binding.textSeeMore.setOnClickListener {
                if (isMaxLine) {
                    binding.textReview.maxLines = 2
                    binding.textSeeMore.text =
                        binding.root.resources.getString(R.string.see_more_caption)
                } else {
                    binding.textReview.maxLines = Integer.MAX_VALUE
                    binding.textSeeMore.text =
                        binding.root.resources.getString(R.string.see_less_caption)
                }

                isMaxLine = !isMaxLine
            }
        }

    }
}