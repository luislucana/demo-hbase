package br.com.hbase;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

// https://www.devmedia.com.br/hadoop-fundamentos-e-instalacao/29466#ambiente
// https://hadoop.apache.org/docs/r2.10.0/hadoop-project-dist/hadoop-common/SingleCluster.html#Required_Software
// https://github.com/steveloughran/winutils
// https://cwiki.apache.org/confluence/display/HADOOP2/Hadoop2OnWindows
// https://cwiki.apache.org/confluence/display/HADOOP2/WindowsProblems
// https://www.baeldung.com/hbase
//install hbase locally & hbase master start
public class HbaseClientExample {

    public static void main(String[] args) throws IOException, ServiceException {
        new HbaseClientExample().connect();
    }

    private void connect() throws IOException, ServiceException {
        Configuration config = HBaseConfiguration.create();

        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

        config.addResource(new Path(path));

        try {
            HBaseAdmin.checkHBaseAvailable(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            return;
        }

        HBaseClientOperations HBaseClientOperations = new HBaseClientOperations();
        HBaseClientOperations.run(config);
    }

}