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

public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static int VEHICLES_INDEX = 0;
    private final static int OVERALL_RATING_INDEX = 1;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split(",");

        if (tokens.length >= 2) {
            String output = tokens[VEHICLES_INDEX] + "," + tokens[OVERALL_RATING_INDEX];
            context.write(new Text(output), new IntWritable(0));
        }
    }
}
