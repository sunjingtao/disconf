package com.baidu.disconf.client.scan.inner.statically.impl;

import com.baidu.disconf.client.common.model.DisConfCommonModel;
import com.baidu.disconf.client.config.DisClientConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author liaoqiqi
 * @version 2014-9-9
 */
public class StaticScannerMgrImplBase {

    /**
     * env/version 默认是应用整合设置的，但用户可以在配置中更改它
     */
    protected static DisConfCommonModel makeDisConfCommonModel(String env, String version) {

        DisConfCommonModel disConfCommonModel = new DisConfCommonModel();

        // app
        disConfCommonModel.setApp(DisClientConfig.getInstance().APP);

        // env
        if (!env.isEmpty()) {
            disConfCommonModel.setEnv(env);
        } else {
            //如果是根据IP定义环境类型，那么直接获取本地IP即可获取配置文件
            if(DisClientConfig.getInstance().USE_IP_DEFINE_ENV){
                try {
                    disConfCommonModel.setEnv(InetAddress.getLocalHost().getHostAddress());
                }catch (UnknownHostException ex){
                    throw new RuntimeException("cann't get ip address to define which env must be used");
                }
            }else {
                disConfCommonModel.setEnv(DisClientConfig.getInstance().ENV);
            }

        }

        // version
        if (!version.isEmpty()) {
            disConfCommonModel.setVersion(version);
        } else {
            disConfCommonModel.setVersion(DisClientConfig.getInstance().VERSION);
        }

        return disConfCommonModel;
    }

}
