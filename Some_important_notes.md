
# _Fragments in Android cheatsheet_


## Fragment Communication in Android via Host Activity

> Communicating between fragments through the host activity using a callback interface is a way to pass data and events between fragments without having direct references to each other. 
> This approach can be useful in situations where the fragment needs to communicate with another fragment but it doesn't have a direct reference to it.

Here's a short revision note on how to communicate between fragments through the host activity using a callback interface:

- Create a callback interface in the fragment that needs to communicate with another fragment
- Implement the callback interface in the host activity
- Have the first fragment call a method on the host activity, passing any necessary data as arguments.
- You can also set the data to be passed in a Bundle, pass it along with the fragment, so the other fragment can receive the data.
- The host activity can then pass that data to the second fragment by calling a method on it that's part of the callback interface.

```java
// Create CallBack Interface
public interface FragmentActionListener {
   void onActionPerformed(Bundle bundle);
}
```

```java
// In the first Fragment


   private FragmentActionListener fragmentActionListener;
   public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }
    // overide onAttach() and onDetach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActionListener) {
            fragmentActionListener = (FragmentActionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentActionListener");
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    // triggers the call back method of host activity passing necessary data as argument
    listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fragmentActionListener != null) {
                    // Create a bundle to store the data
                    Bundle bundle = new Bundle();
                    bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED);
                    bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countries[i]);

                    // Call the method on the host activity, passing the bundle as an argument
                    fragmentActionListener.onActionPerformed(bundle);
                }
            }
        });
```

```java
// Host Activity 
// The host activity implements the FragmentActionListener interface and overrides the necessary function

    @Override
    public void onActionPerformed(Bundle bundle) {
        int actionPerformed = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (actionPerformed){
            case FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED: addCountryDescriptionFragment(bundle); break;
        }
    }
    
    // This method triggers the other fragment
    private void addCountryDescriptionFragment(Bundle bundle){
        fragmentTransaction=fragmentManager.beginTransaction();
        CountryDescriptionFragment countryDescriptionFragment=new CountryDescriptionFragment();
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null); //when user presses back button, the previous fragment (if there is any) will appear, otherwise, it won't have any effect, and the user will go back to the previous activity.
        fragmentTransaction.commit();
    }
```

```java
// The Second Fragment receiving data

      @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        countryName = bundle.getString(FragmentActionListener.KEY_SELECTED_COUNTRY,"India");
        countryDescription = getString(getStringId(countryName));
    }
```


