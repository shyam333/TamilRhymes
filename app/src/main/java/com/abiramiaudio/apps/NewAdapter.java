package com.abiramiaudio.apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    Context context;
    List<Book> mData;


    public NewAdapter(Context context, List<Book> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public NewAdapter.NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflator = LayoutInflater.from(context);
        view = mInflator.inflate(R.layout.new_item, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewViewHolder holder, final int position) {

      //  holder.thumbnailView.setImageResource(mData.get(position).getImage());

        holder.thumbnailView.initialize(DeveloperKey.DEVELOPER_KEY,
                new YouTubeThumbnailView.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                        // youTubeThumbnailLoader.release();

                        youTubeThumbnailLoader.setVideo(mData.get(position).getTitle());

                    }

                    @Override
                    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

        holder.mTextview.setText(mData.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class NewViewHolder extends RecyclerView.ViewHolder {
        public YouTubeThumbnailView thumbnailView;
        public TextView mTextview;



        public NewViewHolder(final View v1) {
            super(v1);

            thumbnailView = (YouTubeThumbnailView) v1.findViewById(R.id.thumbnailview);
            mTextview = (TextView) v1.findViewById(R.id.textview);



            thumbnailView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        String value = mTextview.getText().toString();
                        Intent intent = new Intent(context, PlayerActivity.class);
                        intent.putExtra("key", value);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // intent.putExtra("key",mData.get(position).getTitle());
                        context.startActivity(intent);
                }
            });


        }




    }
}