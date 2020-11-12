package com.mx.gonz.flow.architecture.listener

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.sql.DataSource

abstract class PaginatedScrollListener(
    val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
            ) {
                loadMoreItems()
            }
        }
    }
}
