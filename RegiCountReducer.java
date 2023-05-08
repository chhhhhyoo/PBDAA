import java.io.IOException;
import java.util.*;
     
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class RegiCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    // Use reverseOrder() to get the output in descending order
    private TreeMap<Long, Text> countMap = new TreeMap<Long, Text>(Collections.reverseOrder());
    private IntWritable result = new IntWritable();
    // To get top 20 brands by their sum
    private int count = 0;
    
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
	// Get the sum of Car Counts grouping by brands
        long sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
	// Put in the tree (would be sorted)
        countMap.put(sum, new Text(key.toString()));
    }
    
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Loop over the tree to get outputs
	for (Map.Entry<Long, Text> entry : countMap.entrySet()) {
            long count = entry.getKey();
            Text make = entry.getValue();
            result.set((int) count);
            context.write(make, result);
            // Increment count to keep track and get top 20
	    this.count++; 
            // Break if we got 20
	    if (this.count >= 20) { 
                break;
            }
        }
    }
}
