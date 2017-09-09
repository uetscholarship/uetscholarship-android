
package net.bqc.uetscholarship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by BQC-PC on 9/9/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
        }

        NewsItem item = getItem(position);
        if (item != null) {
            TextView title = convertView.findViewById(R.id.tv_title);
            TextView body = convertView.findViewById(R.id.tv_body);

            title.setText(item.getTitle());
            body.setText(item.getBody());
        }

        return convertView;
    }
}
