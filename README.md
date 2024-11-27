# Encoding
This assignment is focused around secure communication, based on the Fast Fourier Transform, string algorithms, and encryption. It is a team assignment, and you must implement the algorithms, using common data structures amongst your team.  Your team should be between three and five members - you may use prior teams, or create new ones (using either discussion posts or the existing shared Google Sheet with the class roster to identify new partners).

Given a collection of friends and communication paths amongst them (i.e., a graph of people with connections as in Assignment 6), you must develop a series of ways for these friends (and friends-of-friends, etc) to communicate. In all cases:

    The sender does not need to be connected to the receiver.  
    A "message" is a triple of the identity of the sender (a node in the graph), the identity of the receiver (also a node in the graph), any necessary metadata to describe the message, and a message body.
    A "message body" is a simple string of characters.
    People in the graph may have additional data associated with them, as required by the specific communication described.

Choose at least as many of the following as you have people on your team (though you can share the work however you want):

    Send compressed messages  from one person to another using run-length encoding. The metadata should indicate that the message is run-length encoded.
    Send lossy compressed messages from one to another, using fast Fourier transforms A sender may specify how lossy the compression should be. The messages should be "blurry", i.e., fine details can be lost and not sent. The metadata specifies how long the original message was (which will be longer than the actual message body).
    Send encrypted messages from one to another, using RSA encryption.  The sender and receiver will both need both a public key and a private key.
    Send a signed message from one to another. Here, the signature should be the RSA-encrypted hash of the message. The receiver can decrypt the hash and confirm that the hash matches the message by redoing the hash.
    In response to a signed message, return a signed message that confirms that the signed message was received and validated. The returned signed message should thus include the original received signature, the original hash, the hash of the returned message, and the encrypted hash of the returned message.

You will need to think carefully about how to ensure that the metadata and the messages are consistent, and that all of these messages work within a common graph structure. Your entire team must be using the same data structures and the same messaging approaches, even if one member implements the details of one while another implements another.

This is worth 35 points, with fifteen points for the clear explanation and 20 points for the correct implementation.
