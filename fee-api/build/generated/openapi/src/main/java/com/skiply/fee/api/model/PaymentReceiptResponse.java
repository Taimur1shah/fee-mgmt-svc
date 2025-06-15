package com.skiply.fee.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.skiply.fee.api.model.Transaction;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.hateoas.RepresentationModel;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentReceiptResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-14T17:41:35.316386500+04:00[Asia/Dubai]")
public class PaymentReceiptResponse extends RepresentationModel<PaymentReceiptResponse>  {

  @JsonProperty("studentId")
  private Integer studentId;

  @JsonProperty("feeName")
  private String feeName;

  @JsonProperty("grade")
  private String grade;

  @JsonProperty("receiptId")
  private String receiptId;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("amount")
  private Float amount;

  @JsonProperty("transaction")
  private Transaction transaction;

  public PaymentReceiptResponse studentId(Integer studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * Get studentId
   * @return studentId
  */
  @NotNull 
  @Schema(name = "studentId", required = true)
  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  public PaymentReceiptResponse feeName(String feeName) {
    this.feeName = feeName;
    return this;
  }

  /**
   * Get feeName
   * @return feeName
  */
  @NotNull 
  @Schema(name = "feeName", required = true)
  public String getFeeName() {
    return feeName;
  }

  public void setFeeName(String feeName) {
    this.feeName = feeName;
  }

  public PaymentReceiptResponse grade(String grade) {
    this.grade = grade;
    return this;
  }

  /**
   * Get grade
   * @return grade
  */
  @NotNull 
  @Schema(name = "grade", required = true)
  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public PaymentReceiptResponse receiptId(String receiptId) {
    this.receiptId = receiptId;
    return this;
  }

  /**
   * Get receiptId
   * @return receiptId
  */
  @NotNull 
  @Schema(name = "receiptId", required = true)
  public String getReceiptId() {
    return receiptId;
  }

  public void setReceiptId(String receiptId) {
    this.receiptId = receiptId;
  }

  public PaymentReceiptResponse quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */
  
  @Schema(name = "quantity", required = false)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public PaymentReceiptResponse amount(Float amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull 
  @Schema(name = "amount", required = true)
  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public PaymentReceiptResponse transaction(Transaction transaction) {
    this.transaction = transaction;
    return this;
  }

  /**
   * Get transaction
   * @return transaction
  */
  @NotNull @Valid 
  @Schema(name = "transaction", required = true)
  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentReceiptResponse paymentReceiptResponse = (PaymentReceiptResponse) o;
    return Objects.equals(this.studentId, paymentReceiptResponse.studentId) &&
        Objects.equals(this.feeName, paymentReceiptResponse.feeName) &&
        Objects.equals(this.grade, paymentReceiptResponse.grade) &&
        Objects.equals(this.receiptId, paymentReceiptResponse.receiptId) &&
        Objects.equals(this.quantity, paymentReceiptResponse.quantity) &&
        Objects.equals(this.amount, paymentReceiptResponse.amount) &&
        Objects.equals(this.transaction, paymentReceiptResponse.transaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, feeName, grade, receiptId, quantity, amount, transaction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentReceiptResponse {\n");
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    feeName: ").append(toIndentedString(feeName)).append("\n");
    sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
    sb.append("    receiptId: ").append(toIndentedString(receiptId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    transaction: ").append(toIndentedString(transaction)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

