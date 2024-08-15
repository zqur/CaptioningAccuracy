# CaptioningAccuracy
Compare accuracy of caption generation of YouTube and Panopto

Name .txt file downloaded from YouTube "YT1" and place in project directory
Name .txt file downloaded from Panoptp "PT1" and place in project directory.

Compile project and Run Testing.java, resulting in four output .txt files in project directory as follows.

debug1.txt - list of all words extracted from caption text files.

resultsDiff.txt - output of all unique strings from each captioning text file, along with the count of occurences and which file it was extracted from (YT or PT)

resultsEqual.txt - output of all unique strings found in both captioning text files, along with the count of occurences

resultsListDiff.txt - output of all strings and counts, if it is from YT or PT file, and "ZERO" if the word does not appear in the opposing platforms captioning file

resultsListEqual.txt - output of all strings that appear in both YT and PT caption .txt file, as well as the count for that word.
