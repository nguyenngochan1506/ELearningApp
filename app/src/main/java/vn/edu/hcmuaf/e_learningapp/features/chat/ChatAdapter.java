package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.content.Context;


import vn.edu.hcmuaf.e_learningapp.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SELF = 1;
    private static final int VIEW_TYPE_OTHER = 2;

    private List<Chat> messages;
    private String currentUserId;
    private Context context;

    public ChatAdapter(Context context, List<Chat> messages, String currentUserId) {
        this.context = context;
        this.messages = messages;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getItemViewType(int position) {
        // Tạm thời set "me" là tin nhắn của bản thân
        if (messages.get(position).getSenderId().equals(currentUserId)) {
            return VIEW_TYPE_SELF;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SELF) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_message_self, parent, false);
            return new SelfMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_message_other, parent, false);
            return new OtherMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Chat message = messages.get(position);
        if (holder instanceof SelfMessageViewHolder) {
            ((SelfMessageViewHolder) holder).tvMessage.setText(message.getContent());
            ((SelfMessageViewHolder) holder).tvTimestamp.setText(message.getTime());
        } else if (holder instanceof OtherMessageViewHolder) {
            ((OtherMessageViewHolder) holder).tvSender.setText(message.getSenderName());
            ((OtherMessageViewHolder) holder).tvMessage.setText(message.getContent());
            ((OtherMessageViewHolder) holder).tvTimestamp.setText(message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class SelfMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTimestamp;

        SelfMessageViewHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }

    static class OtherMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvSender, tvMessage, tvTimestamp;

        OtherMessageViewHolder(View itemView) {
            super(itemView);
            tvSender = itemView.findViewById(R.id.tv_sender);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
}
