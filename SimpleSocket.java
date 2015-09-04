
public class SimpleSocket {
	int numMessagesSent = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //get the UI items to interact with
        final TextView responseView = 
        (TextView)this.findViewById(R.id.response);
        final EditText textInput = 
        (EditText)this.findViewById(R.id.message);
        try{
        	//connect to the server
	        Socket toServer = new Socket("10.0.2.2", 9292);
	        //setup the JSON streams to be used later.
	        final JSONInputStream inFromServer = 
            new JSONInputStream(toServer.getInputStream());
	        final JSONOutputStream outToServer = 
            new JSONOutputStream(toServer.getOutputStream());
	        
            //add the on click listener to the button
            Button sendButton = 
            (Button)this.findViewById(R.id.sendButton);
            sendButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    numMessagesSent++;
                    //prepare the bean
                    ArrayList aDataList = new ArrayList();
                    aDataList.add(numMessagesSent);
                    aDataList.add(textInput.getText().toString());
                    CommunicationBean aBean = 
                    new CommunicationBean();
                    aBean.setCommand("Speak");
                    aBean.setData(aDataList);
                    
                    try {
                        //send the bean
                        outToServer.writeObject(aBean);
                        HashMap aMap = 
                        (HashMap)inFromServer.readObject();
                        
                        responseView.setText(aMap.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        responseView.setText("Error: Unable to trade beans with server.");
                    }
                }
            });
            
        }
        catch(Exception e){
            e.printStackTrace();
            responseView.setText("Error: Unable to communicate with server. "+e.getLocalizedMessage());
        }
    }
}