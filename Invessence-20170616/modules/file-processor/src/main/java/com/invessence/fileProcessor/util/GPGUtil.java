package com.invessence.fileProcessor.util;


import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.bc.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import java.util.zip.InflaterInputStream;

public class GPGUtil
{

//    private FileInputStream privateKey;
//    private FileInputStream publicKey;
    private char[] password;
    private String privateKeyPath;
    private String publicKeyPath;

    public GPGUtil(String _privateKey, String _publicKey, String _password){

        System.out.println("************************************************************************");
        System.out.println(_privateKey+" : "+_publicKey+"  : "+_password);
        this.privateKeyPath=_privateKey;
        this.publicKeyPath=_publicKey;
//        try
//        {//D:\Project\Abhang\Project Work\ltam\gnupg
//            System.out.println("************************************************************************");
//            System.out.println(_privateKey+" : "+_publicKey+"  : "+_password);
//            this.privateKeyPath=_privateKey;
//            this.publicKeyPath=_publicKey;
////            this.privateKey = new FileInputStream(_privateKey);
////            this.publicKey =new FileInputStream(_publicKey);
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }

        this.password=_password.toCharArray();
    }


    public PGPSecretKey readSecretKeyFromCol(InputStream in, long keyId) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(in, new BcKeyFingerprintCalculator());

        PGPSecretKey key = pgpSec.getSecretKey(keyId);

        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }
        return key;
    }


    @SuppressWarnings("rawtypes")
    public PGPPublicKey readPublicKeyFromCol(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in, new BcKeyFingerprintCalculator());
        PGPPublicKey key = null;
        Iterator rIt = pgpPub.getKeyRings();
        while (key == null && rIt.hasNext()) {
            PGPPublicKeyRing kRing = (PGPPublicKeyRing) rIt.next();
            Iterator kIt = kRing.getPublicKeys();
            while (key == null && kIt.hasNext()) {
                PGPPublicKey k = (PGPPublicKey) kIt.next();
                if (k.isEncryptionKey()) {
                    key = k;
                }
            }
        }
        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }
        return key;
    }

    public void decryptFile(InputStream in, OutputStream out) throws IOException, PGPException, InvalidCipherTextException {
        Security.addProvider(new BouncyCastleProvider());
//         System.out.println("publicKeyPath :"+publicKeyPath);
        PGPPublicKey pubKey = readPublicKeyFromCol(new FileInputStream(publicKeyPath));

        PGPSecretKey secKey = readSecretKeyFromCol(new FileInputStream(privateKeyPath), pubKey.getKeyID());

        in = PGPUtil.getDecoderStream(in);

        JcaPGPObjectFactory pgpFact;


        PGPObjectFactory pgpF = new PGPObjectFactory(in, new BcKeyFingerprintCalculator());

        Object o = pgpF.nextObject();
        PGPEncryptedDataList encList;

        if (o instanceof PGPEncryptedDataList) {

            encList = (PGPEncryptedDataList) o;

        } else {

            encList = (PGPEncryptedDataList) pgpF.nextObject();

        }

        Iterator<PGPPublicKeyEncryptedData> itt = encList.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData encP = null;
        while (sKey == null && itt.hasNext()) {
            encP = itt.next();
            //secKey = readSecretKeyFromCol(new FileInputStream("/home/abhangp/gnupg/secring.gpg"), encP.getKeyID());
            secKey = readSecretKeyFromCol(new FileInputStream(privateKeyPath), encP.getKeyID());
            sKey = secKey.extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(password));
        }
        if (sKey == null) {
            throw new IllegalArgumentException("Secret key for message not found.");
        }

        InputStream clear = encP.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));

        pgpFact = new JcaPGPObjectFactory(clear);

        PGPCompressedData c1 = (PGPCompressedData) pgpFact.nextObject();

        pgpFact = new JcaPGPObjectFactory(c1.getDataStream());

        PGPLiteralData ld = (PGPLiteralData) pgpFact.nextObject();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        InputStream inLd = ld.getDataStream();

        int ch;
        while ((ch = inLd.read()) >= 0) {
            bOut.write(ch);
        }
//        System.out.println("********************************");
//        System.out.println(ld.getFileName());
//        System.out.println(ld.getRawFileName().toString());
//        System.out.println(bOut.toString());

       bOut.writeTo(out);
        out.flush();
        out.close();
//        System.out.println("********************************");
        //return bOut;

    }

    public static void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey) throws IOException, NoSuchProviderException, PGPException {
        Security.addProvider(new BouncyCastleProvider());

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);

        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));

        comData.close();

        PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(new BcPGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.TRIPLE_DES).setSecureRandom(new SecureRandom()));

        cPk.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(encKey));

        byte[] bytes = bOut.toByteArray();

        OutputStream cOut = cPk.open(out, bytes.length);

        cOut.write(bytes);

        cOut.close();

        out.close();
    }

//    public static void main(String[] args) {
//        try {
//            try {
//                decryptFile(new FileInputStream("/data/ltam/Test_NewAccounts_20160219.csv.pgp"), new FileOutputStream("/data/ltam/Test_NewAccounts_20160219.csv"), new FileInputStream("/home/abhangp/gnupg/secring.gpg"), new FileInputStream("/home/abhangp/gnupg/pubring.gpg"), new String("Inv3ss3nc3").toCharArray());
//
//            } catch (InvalidCipherTextException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            try {
//                decryptFile(new FileInputStream("/data/ltam/Test_ShareholderPositions_20160219.csv.pgp"),new FileOutputStream("/data/ltam/Test_ShareholderPositions_20160219.csv"), new FileInputStream("/home/abhangp/gnupg/secring.gpg"), new FileInputStream("/home/abhangp/gnupg/pubring.gpg"), new String("Inv3ss3nc3").toCharArray());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InvalidCipherTextException e) {
//                e.printStackTrace();
//            }
//
//
////           PGPPublicKey pubKey = readPublicKeyFromCol(new FileInputStream("PublicKey.asc"));
////
////           encryptFile(new FileOutputStream("encryptedFileOutput.gpg"), "fileToEncrypt.txt", pubKey);
//
//
//
//
//        } catch (PGPException e) {
//            e.printStackTrace();
//            //  System.out.print("exception: " + e.getMessage(), e.getUnderlyingException());
//        }
//    }
//


}
