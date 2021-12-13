/*
 * Copyright 2021 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package search;

import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.SearchResponse;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class SearchUpdateAttributeConfigTest {

  String productId = "GGOEAAEC172013";

  @Test
  public void testUpdateAttributeConfig() throws IOException, InterruptedException {

    Product product = UpdateAttributeConfiguration.updateProduct(productId);

    Assert.assertEquals(product.getId(), productId);

    Assert.assertTrue(product.containsAttributes("ecofriendly"));
  }

  @Test
  public void testUpdateAttributeConfigPass() throws IOException, InterruptedException {

    Product product = UpdateAttributeConfiguration.updateProduct(productId);

    String productTitle = product.getTitle();

    Assert.assertTrue(productTitle.contains("Sweater"));

    Assert.assertTrue(product.containsAttributes("ecofriendly"));
  }

  @Test
  public void testSearchAttributeConfig() throws IOException, InterruptedException {

    SearchResponse response = SearchAttributeConfig.search();

    String productTitle = response.getResults(0).getProduct().getTitle();

    Assert.assertTrue(productTitle.contains("Sweater"));

    Assert.assertTrue(response.getResults(0).getProduct().containsAttributes("ecofriendly"));

    Assert.assertEquals(1, response.getTotalSize());
  }

}
