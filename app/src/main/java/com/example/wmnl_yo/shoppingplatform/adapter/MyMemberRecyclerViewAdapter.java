//package com.example.wmnl_yo.shoppingplatform.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.wmnl_yo.shoppingplatform.DummyContent.DummyItem;
//import com.example.wmnl_yo.shoppingplatform.R;
//import com.example.wmnl_yo.shoppingplatform.fragment.CategoryFragment;
//
//import java.util.List;
//
//public class MyMemberRecyclerViewAdapter extends RecyclerView.Adapter<MyMemberRecyclerViewAdapter.ViewHolder> {
//
//    private final List<DummyItem> mValues;
//    private final CategoryFragment.OnListFragmentInteractionListener mListener;
//
//    public MyMemberRecyclerViewAdapter(List<DummyItem> items, CategoryFragment.OnListFragmentInteractionListener listener) {
//        mValues = items;
//        mListener = listener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.card_view_list, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.mItem = mValues.get(position);
//        holder.mContentView.setImageResource(mValues.get(position).content);
//        holder.mtitle.setText((mValues.get(position).title));
//        //holder.mdetails.setImageResource(mValues.get(position).details);
//
////        holder.mdetails.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(final View view) {
////                final PopupMenu popmenu = new PopupMenu(holder.mdetails.getContext(), holder.mdetails);
////                popmenu.getMenuInflater().inflate(R.menu.menu_scene, popmenu.getMenu());
////
////                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    public boolean onMenuItemClick(MenuItem item) {
////                        switch (item.getItemId()) {
////                            case R.id.scene_sw:
////
////                                return true;
////
////                            case R.id.info:
////                                final View sitem = LayoutInflater.from(view.getContext()).inflate(R.layout.scene_info_dialog, null);
////                                new AlertDialog.Builder(view.getContext())
////                                        .setTitle("test")
////                                        .setView(sitem)
////                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
////                                            @Override
////                                            public void onClick(DialogInterface dialog, int which) {
////
////                                            }
////                                        })
////                                        .show();
////                                return true;
////
////                            case R.id.delete:
////                                MemberFragment.removeItem(holder);
////                                return true;
////
////                            default:
////                                return false;
////                        }
////                    }
////                });
////                popmenu.show();
////            }
////        });
//
//        //notifyItemInserted(position);
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
////                if (position==0){
////                    Intent i = new Intent(holder.mView.getContext(), WebViewActivity.class);
////                    i.putExtra("key","奶嘴");
////                    holder.mView.getContext().startActivity(i);
////                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final ImageView mContentView;
//        public final TextView mtitle;
//        //public final ImageButton mdetails;
//        public DummyItem mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            mContentView = (ImageView) view.findViewById(R.id.country_photo);
//            mtitle = (TextView) view.findViewById(R.id.country_name);
//            //mdetails = (ImageButton) view.findViewById(R.id.imageButton);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mtitle.getText() + "'";
//        }
//    }
//
//
//}