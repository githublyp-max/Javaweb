import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Goods {

  private String name;
  private Double price;
  private String[] types;
}

//-Xms60m -Xmx60m  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Applications/mat.app/Contents/MacOS/workspace
public class OutOfMemoryTest {

  public static void main(String[] args) throws InterruptedException {
    Map<String, Goods> map = new HashMap<>();
    int counter = 1;
    while (true) {
//      Thread.sleep(10);
      Goods goods = new Goods();
      String[] types = new String[counter];
      for (int i = 0; i < types.length; i++) {
        types[i] = "type" + i;
      }
      goods.setName("hresh" + counter);
      goods.setPrice(Double.valueOf(counter));
      goods.setTypes(types);
      map.put(goods.getName(), goods);
      if (counter % 1000 == 0) {
        System.out.println("put" + counter);
      }
      counter++;
    }

  }
}

