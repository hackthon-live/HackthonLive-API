
package com.iydsj.sw.api.DTO;

import lombok.Data;
import java.util.Date;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Data
public class LivingDTO {

    private int id;

    private String name;

    private String des;

    private int isStart;

    private int tagId;

    private String cId;

    private Date cTime;

    private String pushUrl;

    private String rtmpPullUrl;

    private int uId;

    private String userName;

    private String logoUrl;

}
