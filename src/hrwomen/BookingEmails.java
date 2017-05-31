package hrwomen;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 4/21/17.
 */
public class BookingEmails {

    private static PriorityQueue<Content> contentQueue = new PriorityQueue<Content>(10,new EmailComparator());

    private static class Content {
        private String content;
        private int urgency;
        private int counter;

        public Content(String content , int urgency, int counter){
            this.content = content;
            this.urgency = urgency;
            this.counter = counter;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            Content test = (Content) obj;
            return urgency == test.urgency &&
                    (content == test.content || (content != null && content.equals(test.content)));
        }

        public int hashCode()
        	{
            			int hash = 7;
            			hash = 31 * hash + urgency;
            			hash = 31 * hash + (null == content ? 0 : content.hashCode());
            		return hash;
            }

    }

    private static  int counter = 0;

    private static void store (String content , int urgency)
    {
        contentQueue.offer(new Content(content,urgency,counter));
    }

    private static String getNextEmail(){

            Object email = contentQueue.poll();
            if(email == null){
                return "-1";
            }
            return ((Content)email).content;


    }

    static class EmailComparator implements Comparator<Content>
    {
        @Override
        public int compare(Content e1, Content e2)
        {
            if(e2.urgency == e1.urgency) {
                return e1.counter - e2.counter;
            }
            return e2.urgency - e1.urgency;
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int numQueries = Integer.parseInt(in.nextLine());
        for(int i = 0 ; i < numQueries; i++) {
            counter++;
            String instruction = in.nextLine();
            String[] words = instruction.split(" ");
            if(instruction.startsWith("store")){
                store(words[1],Integer.parseInt(words[2]));
            }
            else if(instruction.startsWith("get_next_email")){
                System.out.println(getNextEmail());
            }

        }
    }
}
