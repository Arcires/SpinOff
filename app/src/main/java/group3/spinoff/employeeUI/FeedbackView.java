package group3.spinoff.employeeUI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

import group3.spinoff.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedbackView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedbackView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static String title = "Test";
    private static String description = "";
    private static String comment = "Test Comment";
    private static float q1 = 0;
    private static float q2 = 0;
    private static float q3 = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button backArrow;
    private TextView titleView;
    private TextView descriptionView;
    private RatingBar ratingBarView_Q1;
    private RatingBar ratingBarView_Q2;
    private RatingBar ratingBarView_Q3;
    private TextView commentView;

    public FeedbackView() {
        // Required empty public constructor
    }

    public static void setValues(String d_title, String d_description, String d_comment
                                , float d_q1, float d_q2, float d_q3){
        title = d_title;
        description = d_description;
        comment = d_comment;
        q1 = d_q1;
        q2 = d_q2;
        q3 = d_q3;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackView.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackView newInstance(String param1, String param2) {
        FeedbackView fragment = new FeedbackView();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback_view, container, false);

        titleView = view.findViewById(R.id.fragmentFeedbackTitle);
        descriptionView = view.findViewById(R.id.fragmentFeedbackDescription);
        commentView = view.findViewById(R.id.fragmentFeedbackComment);

        ratingBarView_Q1 = view.findViewById(R.id.ratingBarFeedback1);
        ratingBarView_Q2 = view.findViewById(R.id.ratingBarFeedback2);
        ratingBarView_Q3 = view.findViewById(R.id.ratingBarFeedback3);

        titleView.setText(title);
        descriptionView.setText(description);
        commentView.setText(comment);

        ratingBarView_Q1.setRating(q1);
        ratingBarView_Q2.setRating(q2);
        ratingBarView_Q3.setRating(q3);

        backArrow = view.findViewById(R.id.fragmentFeedbackBackButton);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Doesn't actually go back, but rather recreates the previous fragment again.
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
