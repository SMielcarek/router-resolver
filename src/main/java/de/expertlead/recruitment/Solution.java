package de.expertlead.recruitment;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String route = bufferedReader.readLine();
        String url = bufferedReader.readLine();

        String result = Result.ioWrapper(route, url);
        System.out.println(result);

        bufferedReader.close();
    }
}