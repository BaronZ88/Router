package com.baronzhang.android.router.compiler.utils;

import com.baronzhang.android.router.compiler.Constants;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.baronzhang.android.router.compiler.Constants.PARCELABLE;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2017/3/17
 */
public final class TypeTools {

    private TypeMirror parcelableType;
    private TypeMirror serializableType;

    private Types typeUtils;

    public TypeTools(Types typeUtils, Elements elementUtils) {

        this.typeUtils = typeUtils;

        this.parcelableType = elementUtils.getTypeElement(PARCELABLE).asType();
        this.serializableType = elementUtils.getTypeElement(Constants.SERIALIZABLE).asType();
    }

    public int convertType(TypeMirror typeMirror) {

        switch (typeMirror.toString()) {
            case Constants.INTEGER:
                return Constants.TYPE_KIND.TYPE_INTEGER;
            case Constants.LONG:
                return Constants.TYPE_KIND.TYPE_LONG;
            case Constants.FLOAT:
                return Constants.TYPE_KIND.TYPE_FLOAT;
            case Constants.DOUBLE:
                return Constants.TYPE_KIND.TYPE_DOUBLE;
            case Constants.SHORT:
                return Constants.TYPE_KIND.TYPE_SHORT;
            case Constants.BYTE:
                return Constants.TYPE_KIND.TYPE_BYTE;
            case Constants.STRING:
                return Constants.TYPE_KIND.TYPE_STRING;
            default:
                if (typeUtils.isSubtype(typeMirror, parcelableType)) {
                    return Constants.TYPE_KIND.TYPE_PARCELABLE;
                } else if (typeUtils.isSubtype(typeMirror, serializableType)) {
                    return Constants.TYPE_KIND.TYPE_SERIALIZABLE;
                } else {
                    return Constants.TYPE_KIND.TYPE_OTHER_OBJECT;
                }
        }
    }
}
