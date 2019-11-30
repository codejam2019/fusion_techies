package com.xyz.query.exception;

import com.xyz.query.dto.UniqueRequestIdentifier;
import com.xyz.query.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  @Qualifier("requestIdentifier")
  private UniqueRequestIdentifier requestIdentifier;

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    GenericResponse exceptionResponse =
        GenericResponse.builder()
            .message("We are facing some strange issue please try again.")
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .trackingId(requestIdentifier.getValue())
            .build();
    log.error("Error:- "+ requestIdentifier.getValue() + " : ",ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GrabbyException.class)
  public final ResponseEntity<GenericResponse> handleGrabbyException(
      GrabbyException e, WebRequest request) {
    GenericResponse exceptionResponse =
        GenericResponse.builder()
            .message(e.getMessage())
            .status(e.getStatus())
            .build();
    if (e.getStatus() == 0) {
      log.error(e.getMessage() + ":- " + requestIdentifier.getValue(), e);
      exceptionResponse.setStatus(INTERNAL_SERVER_ERROR.value());
      exceptionResponse.setTrackingId(requestIdentifier.getValue());
      return new ResponseEntity<GenericResponse>(
          exceptionResponse, INTERNAL_SERVER_ERROR);
    }
    log.info("Returning with message: {}" ,e.getMessage());
    return new ResponseEntity<GenericResponse>(
        exceptionResponse, valueOf(e.getStatus()));
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(BAD_REQUEST.value())
                                            .message(ex.getParameterName() + " parameter is missing")
                                            .timestamp(LocalDateTime.now())
                                            .build();
    return new ResponseEntity(
        exceptionResponse, BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(UNSUPPORTED_MEDIA_TYPE.value())
                                            .message(ex.getContentType() + " media type is not supported")
                                            .build();
    return new ResponseEntity(
        exceptionResponse, UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(BAD_REQUEST.value())
                                            .message("Validation error")
                                            .build();
    exceptionResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
    exceptionResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
    return new ResponseEntity(
        exceptionResponse, BAD_REQUEST);
  }

  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolation(
      javax.validation.ConstraintViolationException ex) {
    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(BAD_REQUEST.value())
                                            .message("Validation error")
                                            .build();
    exceptionResponse.addValidationErrors(ex.getConstraintViolations());
    return new ResponseEntity(
        exceptionResponse, BAD_REQUEST);
  }


  /*@ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      EntityNotFoundException ex) {
    ApiError apiError = new ApiError(NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }*/

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(BAD_REQUEST.value())
                                            .message("Malformed JSON request")
                                            .build();
    return new ResponseEntity(exceptionResponse, BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(INTERNAL_SERVER_ERROR.value())
                                            .message("Error writing JSON output")
                                            .trackingId(requestIdentifier.getValue())
                                            .build();
    log.error("Error:-> "+ requestIdentifier.getValue() + " : ",ex);
    return new ResponseEntity(exceptionResponse, INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    GenericResponse exceptionResponse =
        GenericResponse.builder()
            .message("Could not find the path")
            .status(NOT_FOUND.value())
            .path(ex.getRequestURL())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
  }

  /*@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
    return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
  }*/

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                WebRequest request) {
    GenericResponse exceptionResponse;
    if (ex.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException e = (ConstraintViolationException) ex.getCause();
      SQLException sqlException = e.getSQLException();
      exceptionResponse = GenericResponse.builder()
                                              .status(CONFLICT.value())
                                              .message(sqlException.getMessage())
                                              .timestamp(LocalDateTime.now())
                                              .build();
    } else {
      exceptionResponse = GenericResponse.builder()
                              .status(INTERNAL_SERVER_ERROR.value())
                              .message(ex.getMessage())
                              .timestamp(LocalDateTime.now())
                              .trackingId(requestIdentifier.getValue())
                              .build();
    }
    log.error("Error:-> "+ requestIdentifier.getValue() + " : ",ex);
    return new ResponseEntity(exceptionResponse, CONFLICT);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                    WebRequest request) {
    GenericResponse exceptionResponse = GenericResponse.builder()
                                            .status(BAD_REQUEST.value())
                                            .message(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()))
                                            .timestamp(LocalDateTime.now())
                                            .build();
    return new ResponseEntity(
        exceptionResponse, BAD_REQUEST);
  }
}
