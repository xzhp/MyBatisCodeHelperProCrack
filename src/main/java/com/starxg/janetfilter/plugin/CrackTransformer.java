package com.starxg.janetfilter.plugin;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.security.ProtectionDomain;
import java.util.Objects;

public class CrackTransformer implements MyTransformer {
    @Override
    public String getHookClassName() {
        return "com/ccnode/codegenerator/T/c/a";
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {

        // 不是 IDEA 的插件加载类不处理
        if (!Objects.equals("PluginClassLoader", loader.getClass().getSimpleName())) {
            return classBytes;
        }

        try {


            final ClassPool pool = ClassPool.getDefault();
            pool.appendSystemPath();

            // 添加依赖
            {
                final Object pluginDescriptor = loader.getClass().getDeclaredMethod("getPluginDescriptor")
                        .invoke(loader);

                if (!Objects.equals(pluginDescriptor.getClass()
                        .getDeclaredMethod("getVersion").invoke(pluginDescriptor), "3.2.1")) {
                    System.err.println("目前仅支持 3.2.1 版本 https://plugins.jetbrains.com/plugin/9837-mybatiscodehelperpro/versions/stable/355781");
                    return classBytes;
                }

                final Path file = (Path) pluginDescriptor.getClass().getDeclaredMethod("getPluginPath").invoke(pluginDescriptor);
                final File[] files = new File(file.toFile(), "lib")
                        .listFiles((dir, name) -> name.startsWith("MyBatisCodeHelper-Pro") && name.endsWith(".jar"));
                if (Objects.nonNull(files)) {
                    for (File e : files) {
                        pool.appendClassPath(e.getAbsolutePath());
                    }
                }
            }


            final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));
            // rename
            clazz.getDeclaredMethod("a", new CtClass[]{pool.get("java.lang.String")}).setName("a0");

            clazz.addMethod(CtMethod.make("public static com.ccnode.codegenerator.T.e.b a (java.lang.String s){ " +
                            "com.ccnode.codegenerator.T.e.b a = a0(s);" +
                            // paidKey
                            "a.b(java.util.UUID.randomUUID().toString().replace(\"-\",\"\"));" +
                            //  valid
                            "a.a(java.lang.Boolean.TRUE);" +
                            //  validTo 2099 年
                            "a.a(java.lang.Long.valueOf(4081698416000L + new java.util.Random().nextInt(100000000)));" +
                            "return a;" +
                            "}",
                    clazz));


            classBytes = clazz.toBytecode();
            clazz.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classBytes;
    }


}
