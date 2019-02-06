package edu.andrews.cptr252.arn.quoteoftheday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main activity for the application
 * Displays a series of quotes
 */

public class QuoteActivity extends AppCompatActivity {
    /** Key for fact about author stored in Intent sent to AuthorFactActivity. */
    public static final String EXTRA_AUTHOR_FACT = "edu.andrews.cptr252.arn.quoteoftheday.author_fact";
    private static final String KEY_QUOTE_INDEX = "quoteIndex";

    private TextView mQuoteTextView;
    private TextView mAuthorTextView;
    private Button mNextButton;

    /** Quotes used in app */
    private Quote[] mQuoteList = new Quote[] {
            new Quote(R.string.quote_text_0,
                    R.string.quote_author_0,
                    R.string.author_fact_0),

            new Quote(R.string.quote_text_1,
                    R.string.quote_author_1,
                    R.string.author_fact_1),

            new Quote(R.string.quote_text_2,
                    R.string.quote_author_2,
                    R.string.author_fact_2)
    };

    /** Index of current in list */
    private int mCurrentIndex = 0;

    /** Launch activity to display author fact */
    private void displayAuthorFact() {
        Intent i = new Intent(QuoteActivity.this, AuthorFactActivity.class);
        i.putExtra(EXTRA_AUTHOR_FACT, mQuoteList[mCurrentIndex].getAuthorFact());
        startActivity(i);
    }

    /** Display the quote at the current index */
    private void updateQuote() {
        int quote = mQuoteList[mCurrentIndex].getQuote();
        int author = mQuoteList[mCurrentIndex].getAuthor();

        mQuoteTextView.setText(quote);
        mAuthorTextView.setText(author);
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

    /**
     * Setup and inflate layout.
     * @param savedInstanceState Previously saved Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        // Re-display the same quote we were on when activity destroyed
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_QUOTE_INDEX);
        }

        // Display the text for the quote
        mQuoteTextView = findViewById(R.id.quoteTextView);
        int quote = mQuoteList[mCurrentIndex].getQuote();
        mQuoteTextView.setText(quote);
        mQuoteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAuthorFact();
            }
        });

        // Display the author of the quote
        mAuthorTextView = findViewById(R.id.authorTextView);
        int author = mQuoteList[mCurrentIndex].getAuthor();
        mAuthorTextView.setText(author);
        mAuthorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAuthorFact();
            }
        });

        // Setup listener to handle next button presses
        mNextButton = findViewById(R.id.nextButton);
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
    }
}
