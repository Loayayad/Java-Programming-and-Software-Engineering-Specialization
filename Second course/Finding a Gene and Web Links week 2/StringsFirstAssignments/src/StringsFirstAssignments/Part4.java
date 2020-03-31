package StringsFirstAssignments;

import edu.duke.URLResource;

class Part4 {

  int count = 0;

  public void findLinks() {

    URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

    for (String item : file.words()) {
      String itemLower = item.toLowerCase();
      int pos = itemLower.indexOf("youtube.com");

      if (pos != -1) {

        count = count + 1;
        // int beg = itemLower.lastIndexOf("/", pos);
        // int end = itemLower.indexOf("/", pos + 1);
        // System.out.println(itemLower.substring(beg + 1, end));

      }

    }
    System.out.println(count);
  }
}
