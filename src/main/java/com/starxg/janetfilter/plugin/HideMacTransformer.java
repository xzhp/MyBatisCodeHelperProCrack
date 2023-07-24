package com.starxg.janetfilter.plugin;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.security.ProtectionDomain;

public class HideMacTransformer implements MyTransformer {
    @Override
    public String getHookClassName() {
        return "com/ccnode/codegenerator/Z/L";
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {
        try {
            final ClassPool pool = ClassPool.getDefault();
            final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));

            final CtMethod method = clazz.getDeclaredMethod("b");
            method.insertBefore("if(true)return \"AB-CD-EF-GH-IJ-KL\";");

            classBytes = clazz.toBytecode();

            clazz.detach();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classBytes;
    }


}
