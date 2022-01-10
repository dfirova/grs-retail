/*
 * Copyright 2021 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package product.setup;

import static product.setup.SetupCleanup.createBucket;
import static product.setup.SetupCleanup.uploadObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import lombok.Getter;

public class ProductsCreateGcsBucket {

  private static final String PROJECT_ID = System.getenv("PROJECT_ID");

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
      "yyyy-MM-dd_HH-mm-ss");

  private static final Timestamp timestamp = Timestamp.from(Instant.now());

  @Getter
  private static final String bucketName = String.format("%s_products_%s",
      PROJECT_ID, dateFormat.format(timestamp));

  public static void main(String[] args) throws IOException {
    productsCreateGcsBucketAndUploadJsonFiles();
  }

  public static void productsCreateGcsBucketAndUploadJsonFiles()
      throws IOException {

    createBucket(bucketName);

    uploadObject(bucketName, "products.json",
        "src/main/resources/products.json");

    uploadObject(bucketName, "products_some_invalid.json",
        "src/main/resources/products_some_invalid.json");
  }
}
