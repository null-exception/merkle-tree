/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *//**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.hashtrees.thrift.generated;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains nodeId and segment hash.
 * 
 * 
 */
public class SegmentHash implements org.apache.thrift.TBase<SegmentHash, SegmentHash._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SegmentHash");

  private static final org.apache.thrift.protocol.TField NODE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("nodeId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField HASH_FIELD_DESC = new org.apache.thrift.protocol.TField("hash", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SegmentHashStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SegmentHashTupleSchemeFactory());
  }

  public int nodeId; // required
  public ByteBuffer hash; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NODE_ID((short)1, "nodeId"),
    HASH((short)2, "hash");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NODE_ID
          return NODE_ID;
        case 2: // HASH
          return HASH;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NODEID_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NODE_ID, new org.apache.thrift.meta_data.FieldMetaData("nodeId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.HASH, new org.apache.thrift.meta_data.FieldMetaData("hash", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SegmentHash.class, metaDataMap);
  }

  public SegmentHash() {
  }

  public SegmentHash(
    int nodeId,
    ByteBuffer hash)
  {
    this();
    this.nodeId = nodeId;
    setNodeIdIsSet(true);
    this.hash = hash;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SegmentHash(SegmentHash other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.nodeId = other.nodeId;
    if (other.isSetHash()) {
      this.hash = org.apache.thrift.TBaseHelper.copyBinary(other.hash);
;
    }
  }

  public SegmentHash deepCopy() {
    return new SegmentHash(this);
  }

  @Override
  public void clear() {
    setNodeIdIsSet(false);
    this.nodeId = 0;
    this.hash = null;
  }

  public int getNodeId() {
    return this.nodeId;
  }

  public SegmentHash setNodeId(int nodeId) {
    this.nodeId = nodeId;
    setNodeIdIsSet(true);
    return this;
  }

  public void unsetNodeId() {
    __isset_bit_vector.clear(__NODEID_ISSET_ID);
  }

  /** Returns true if field nodeId is set (has been assigned a value) and false otherwise */
  public boolean isSetNodeId() {
    return __isset_bit_vector.get(__NODEID_ISSET_ID);
  }

  public void setNodeIdIsSet(boolean value) {
    __isset_bit_vector.set(__NODEID_ISSET_ID, value);
  }

  public byte[] getHash() {
    setHash(org.apache.thrift.TBaseHelper.rightSize(hash));
    return hash == null ? null : hash.array();
  }

  public ByteBuffer bufferForHash() {
    return hash;
  }

  public SegmentHash setHash(byte[] hash) {
    setHash(hash == null ? (ByteBuffer)null : ByteBuffer.wrap(hash));
    return this;
  }

  public SegmentHash setHash(ByteBuffer hash) {
    this.hash = hash;
    return this;
  }

  public void unsetHash() {
    this.hash = null;
  }

  /** Returns true if field hash is set (has been assigned a value) and false otherwise */
  public boolean isSetHash() {
    return this.hash != null;
  }

  public void setHashIsSet(boolean value) {
    if (!value) {
      this.hash = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NODE_ID:
      if (value == null) {
        unsetNodeId();
      } else {
        setNodeId((Integer)value);
      }
      break;

    case HASH:
      if (value == null) {
        unsetHash();
      } else {
        setHash((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NODE_ID:
      return Integer.valueOf(getNodeId());

    case HASH:
      return getHash();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NODE_ID:
      return isSetNodeId();
    case HASH:
      return isSetHash();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SegmentHash)
      return this.equals((SegmentHash)that);
    return false;
  }

  public boolean equals(SegmentHash that) {
    if (that == null)
      return false;

    boolean this_present_nodeId = true;
    boolean that_present_nodeId = true;
    if (this_present_nodeId || that_present_nodeId) {
      if (!(this_present_nodeId && that_present_nodeId))
        return false;
      if (this.nodeId != that.nodeId)
        return false;
    }

    boolean this_present_hash = true && this.isSetHash();
    boolean that_present_hash = true && that.isSetHash();
    if (this_present_hash || that_present_hash) {
      if (!(this_present_hash && that_present_hash))
        return false;
      if (!this.hash.equals(that.hash))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SegmentHash other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SegmentHash typedOther = (SegmentHash)other;

    lastComparison = Boolean.valueOf(isSetNodeId()).compareTo(typedOther.isSetNodeId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNodeId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nodeId, typedOther.nodeId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHash()).compareTo(typedOther.isSetHash());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHash()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hash, typedOther.hash);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SegmentHash(");
    boolean first = true;

    sb.append("nodeId:");
    sb.append(this.nodeId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("hash:");
    if (this.hash == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.hash, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'nodeId' because it's a primitive and you chose the non-beans generator.
    if (hash == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'hash' was not present! Struct: " + toString());
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SegmentHashStandardSchemeFactory implements SchemeFactory {
    public SegmentHashStandardScheme getScheme() {
      return new SegmentHashStandardScheme();
    }
  }

  private static class SegmentHashStandardScheme extends StandardScheme<SegmentHash> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SegmentHash struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NODE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.nodeId = iprot.readI32();
              struct.setNodeIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HASH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.hash = iprot.readBinary();
              struct.setHashIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetNodeId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'nodeId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SegmentHash struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NODE_ID_FIELD_DESC);
      oprot.writeI32(struct.nodeId);
      oprot.writeFieldEnd();
      if (struct.hash != null) {
        oprot.writeFieldBegin(HASH_FIELD_DESC);
        oprot.writeBinary(struct.hash);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SegmentHashTupleSchemeFactory implements SchemeFactory {
    public SegmentHashTupleScheme getScheme() {
      return new SegmentHashTupleScheme();
    }
  }

  private static class SegmentHashTupleScheme extends TupleScheme<SegmentHash> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SegmentHash struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.nodeId);
      oprot.writeBinary(struct.hash);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SegmentHash struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.nodeId = iprot.readI32();
      struct.setNodeIdIsSet(true);
      struct.hash = iprot.readBinary();
      struct.setHashIsSet(true);
    }
  }

}

