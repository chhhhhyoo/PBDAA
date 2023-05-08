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

public class RegiCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    // We want column Make which shows brand name
    // and column Registered Class to get the registered car count
    private static final int MAKE_INDEX = 1;
    private static final int REG_CLASS_INDEX = 3;
    private static final double proportionRate = 0.2003; 
	
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] columns = line.split(",");

        // Skip rows if not VEH
        if (!columns[0].equals("VEH")) {
            return;
        }

        String make = columns[MAKE_INDEX];
	Integer regClass = Integer.parseInt(columns[REG_CLASS_INDEX]);

   	int adjustedCount = (int) (regClass * proportionRate);

        context.write(new Text(make), new IntWritable(adjustedCount));
    }
}
