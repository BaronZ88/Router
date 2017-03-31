/*
 * getIntent().getStringExtra("");
 getIntent().getStringArrayExtra("");
 getIntent().getStringArrayListExtra("");

 getIntent().getIntExtra("", 0);
 getIntent().getIntArrayExtra("");
 getIntent().getIntegerArrayListExtra("");

 getIntent().getLongExtra("", 0);
 getIntent().getLongArrayExtra("");

 getIntent().getDoubleExtra("", 0);
 getIntent().getDoubleArrayExtra("");

 getIntent().getFloatExtra("", 0);
 getIntent().getFloatArrayExtra("");

 getIntent().getShortExtra("", (short) 0);
 getIntent().getShortArrayExtra("");

 getIntent().getParcelableExtra("");
 getIntent().getParcelableArrayExtra("");
 getIntent().getParcelableArrayListExtra("");

 getIntent().getSerializableExtra("");

 getIntent().getCharSequenceExtra("");
 getIntent().getCharSequenceArrayExtra("");
 getIntent().getCharSequenceArrayListExtra("");*/

package com.baronzhang.android.router.compiler.processor;

import com.baronzhang.android.router.annotations.inject.Inject;
import com.baronzhang.android.router.annotations.inject.InjectUriParam;
import com.baronzhang.android.router.compiler.Constants;
import com.baronzhang.android.router.compiler.utils.TypeTools;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2017/3/14
 */
final class TargetClass {

    private static final ClassName PARAMETERS_INJECTOR = ClassName.get("com.baronzhang.android.router", "ParametersInjector");

    private List<FiledInjecting> filedInjectingList;

    private final TypeTools typeTools;
    private final TypeName targetTypeName;
    private final ClassName injectingClassName;

    TargetClass(TypeTools typeTools, TypeName targetTypeName, ClassName injectingClassName) {

        this.filedInjectingList = new ArrayList<>();

        this.typeTools = typeTools;
        this.targetTypeName = targetTypeName;
        this.injectingClassName = injectingClassName;
    }

    void addField(FiledInjecting filedInjecting) {
        filedInjectingList.add(filedInjecting);
    }

    JavaFile brewJava() {

        TypeName targetType = TypeVariableName.get("T");

        TypeSpec.Builder builder = TypeSpec.classBuilder(injectingClassName.simpleName())
                .addModifiers(Modifier.PUBLIC)
                .addTypeVariable(TypeVariableName.get("T", targetTypeName))
                .addSuperinterface(PARAMETERS_INJECTOR)
                .addMethod(createInjectMethod(targetType));

//        for (FiledInjecting filed : filedInjectingList) {
//            builder.addField(TypeName.get(filed.getTypeMirror()), filed.getName());
//        }

        return JavaFile.builder(injectingClassName.packageName(), builder.build())
                .addFileComment("Generated code from Router. Do not modify!")
                .build();
    }

    /**
     * 生成inject方法代码
     *
     * @return method code
     */
    private MethodSpec createInjectMethod(TypeName targetType) {

        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(targetType, "target");

        for (FiledInjecting filed : filedInjectingList) {

            if (filed.getAnnotation().equals(Inject.class)) {

                CodeBlock codeBlock = CodeBlock.builder().add("target.$L = ", filed.getName())
                        .add("target.getIntent().")
                        .add(buildStatement(typeTools.convertType(filed.getTypeMirror()), true), filed.getParamKey()).build();
                builder.addCode(codeBlock);

            } else if (filed.getAnnotation().equals(InjectUriParam.class)) {

                if (typeTools.convertType(filed.getTypeMirror()) == Constants.TYPE_KIND.TYPE_STRING) {
                    CodeBlock codeBlock = CodeBlock.builder().add("target.$L = ", filed.getName())
                            .add("target.getIntent().getData().getQueryParameter($S);", filed.getParamKey())
                            .build();
                    builder.addCode(codeBlock);
                } else {
                    //抛出异常，提示被注解的字段必须是String类型
                    throw new IllegalArgumentException(filed.getName() + " must be a String type");
                }
            }
            builder.addCode("\n");
        }
        return builder.build();
    }

    private String buildStatement(int type, boolean isActivity) {
        String statement = "";
        switch (type) {
            case Constants.TYPE_KIND.TYPE_INTEGER:
                statement += (isActivity ? ("getIntExtra($S, 0)") : ("getInt($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_LONG:
                statement += (isActivity ? ("getLongExtra($S, 0)") : ("getLong($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_FLOAT:
                statement += (isActivity ? ("getFloatExtra($S, 0)") : ("getFloat($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_DOUBLE:
                statement += (isActivity ? ("getDoubleExtra($S, 0)") : ("getDouble($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_SHORT:
                statement += (isActivity ? ("getShortExtra($S, (short) 0)") : ("getShort($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_BYTE:
                statement += (isActivity ? ("getByteExtra($S, (byte) 0)") : ("getByte($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_BOOLEAN:
                statement += (isActivity ? ("getBooleanExtra($S, false)") : ("getBoolean($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_STRING:
                statement += (isActivity ? ("getStringExtra($S)") : ("getString($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_PARCELABLE:
                statement += (isActivity ? ("getParcelableExtra($S)") : ("getParcelable($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_SERIALIZABLE:
                statement += (isActivity ? ("getSerializableExtra($S)") : ("getSerializable($S)"));
                break;
            case Constants.TYPE_KIND.TYPE_OTHER_OBJECT:
                statement = "$T.parseObject(" + (isActivity ? "substitute.getIntent()." : "getArguments(). ") + "getStringExtra($S), $T.class)";
                break;
        }
        return statement + ";";
    }
}