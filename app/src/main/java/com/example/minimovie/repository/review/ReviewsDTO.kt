package com.example.minimovie.repository.review

data class ReviewsDTO(
    var id: Int?,
    var page: Int?,
    var results: List<ResultReviews>?,
    var total_pages: Int?,
    var total_results: Int?
)

data class ResultReviews(
    var author: String?,
    var author_details: AuthorDetails?,
    var content: String?,
    var created_at: String?,
    var id: String?,
    var update_at: String?,
    var url: String?
)

data class AuthorDetails(
    var avatar_path: String?,
    var name: String?,
    var rating: Int?,
    var username: String?
) {

    val formatterRating
        get() = run {
            if (rating == null) {
                0.toString()
            } else{
                rating.toString()
            }
        }
}

