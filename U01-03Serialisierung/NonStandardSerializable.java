
//******************************************************************************
//  OOP2 : Personal Registration                                          vtg  *
//         Interface for non-standard serialization                            *
// --------------------------------------------------------------------------- *
//  version 1    : 2011-02-01                                                  *
//******************************************************************************

public interface NonStandardSerializable{
  byte[] serialize();
  void deserialize(byte[] b);
  int datasetLength();
}