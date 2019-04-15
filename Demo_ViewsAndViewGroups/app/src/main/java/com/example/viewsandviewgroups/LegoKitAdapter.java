package com.example.viewsandviewgroups;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Demo_ViewsAndViewGroups
 *
 * Adapter that demonstrates how to display LegoKit objects in a ListView
 * @version 2019-04-14
 */
public class LegoKitAdapter extends ArrayAdapter<LegoKit> {

    private final Context context;
    private final LegoKit[] kits;


    public LegoKitAdapter(Context context, LegoKit[] values) {
        super(context, -1, values);
        this.context = context;
        this.kits = values;
    }

    /**
     * Generates the actual View objects shown as ListView items.
     * @param position the index of the item being created
     * @param convertView convertView
     * @param parent parent viewGroup
     * @return a LinearLayout as a View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(context);
        title.setText(kits[position].getTitle());

        TextView id = new TextView(context);
        id.setText(context.getResources().getString(R.string.listview_id)
                + kits[position].getId());

        TextView numPieces = new TextView(context);
        numPieces.setText(context.getResources()
                .getString(R.string.listview_pieces) +
                Integer.toString(kits[position].getNumberOfPieces()));

        layout.addView(title);
        layout.addView(id);
        layout.addView(numPieces);
        return layout;
    }
}
