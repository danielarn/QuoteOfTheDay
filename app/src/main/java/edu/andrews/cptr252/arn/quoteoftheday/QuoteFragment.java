package edu.andrews.cptr252.arn.quoteoftheday;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Main activity for the application
 * Displays a series of quotes
 */

public class QuoteFragment extends Fragment {

    /** Key for fact about author stored in Intent sent to AuthorFactActivity. */
    public static final String EXTRA_AUTHOR_FACT = "edu.andrews.cptr252.arn.quoteoftheday.author_fact";
    /** Key for preserving current quote when rotating device */
    private static final String KEY_QUOTE_INDEX = "quoteIndex";
    /** ImageView used to display inspirational image */
    private ImageView mImageView;
    /** TextView used to display a quote */
    private TextView mQuoteTextView;
    /** TextView used to display a quote's author */
    private TextView mAuthorTextView;
    /** Button used to navigate to the next quote */
    private Button mNextButton;

    /** Quotes used in app */
    private Quote[] mQuoteList = new Quote[] {
            new Quote(R.string.quote_text_0,
                    R.string.quote_author_0,
                    R.string.author_fact_0,
                    R.drawable.image_0),

            new Quote(R.string.quote_text_1,
                    R.string.quote_author_1,
                    R.string.author_fact_1,
                    R.drawable.image_1),

            new Quote(R.string.quote_text_2,
                    R.string.quote_author_2,
                    R.string.author_fact_2,
                    R.drawable.image_2),

            new Quote(R.string.quote_text_3,
                    R.string.quote_author_3,
                    R.string.author_fact_3,
                    R.drawable.image_3),

            new Quote(R.string.quote_text_4,
                    R.string.quote_author_4,
                    R.string.author_fact_4,
                    R.drawable.image_4)
    };

    /** Index of current in list */
    private int mCurrentIndex = 0;

    /** Launch activity to display author fact */
    private void displayAuthorFact() {
        Intent i = new Intent(getActivity(), AuthorFactActivity.class);
        i.putExtra(EXTRA_AUTHOR_FACT, mQuoteList[mCurrentIndex].getAuthorFact());
        startActivity(i);
    }

    /** Display the quote at the current index */
    private void updateQuote() {
        int quote = mQuoteList[mCurrentIndex].getQuote();
        int author = mQuoteList[mCurrentIndex].getAuthor();
        int image = mQuoteList[mCurrentIndex].getImage();

        mQuoteTextView.setText(quote);
        mAuthorTextView.setText(author);
        mImageView.setImageResource(image);
    }

    /**
     * Remember the current quote when the activity is destroyed
     * @param savedInstanceState Bundle used for saving identity of current quote.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Store the index of hte current quote in the Bundle
        // Use our key to access the value later
        savedInstanceState.putInt(KEY_QUOTE_INDEX, mCurrentIndex);
    }

    public QuoteFragment() {
        // Required empty public constructor
    }

    /**
     * Setup and inflate layout.
     * @param savedInstanceState Previously saved Bundle
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote, container, false);

        // Re-display the same quote we were on when activity destroyed
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_QUOTE_INDEX);
        }

        // Display image
        mImageView = v.findViewById(R.id.imageView);
        int image = mQuoteList[mCurrentIndex].getImage();
        mImageView.setImageResource(image);

        // Display the text for the quote
        mQuoteTextView = v.findViewById(R.id.quoteTextView);
        int quote = mQuoteList[mCurrentIndex].getQuote();
        mQuoteTextView.setText(quote);
        mQuoteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAuthorFact();
            }
        });

        // Display the author of the quote
        mAuthorTextView = v.findViewById(R.id.authorTextView);
        int author = mQuoteList[mCurrentIndex].getAuthor();
        mAuthorTextView.setText(author);
        mAuthorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAuthorFact();
            }
        });

        // Setup listener to handle next button presses
        mNextButton = v.findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * move to next quote in the list
                 * if index reaches end of array,
                 * reset index to zero
                 */
                mCurrentIndex++;
                if (mCurrentIndex == mQuoteList.length) {
                    mCurrentIndex = 0;
                }
                updateQuote();
            }
        });

        return v;
    }

}
