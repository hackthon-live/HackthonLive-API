package com.iydsj.sw.common.fileresource.dao.impl;

import com.iydsj.sw.common.dao.AbstractBaseDao;
import com.iydsj.sw.common.dao.ParamsMap;
import com.iydsj.sw.common.dao.SqlBuilder;
import com.iydsj.sw.common.fileresource.dao.FileResourceDao;
import com.iydsj.sw.common.fileresource.entity.FileResource;
import com.iydsj.sw.common.utils.DateUtils;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yanyan.wang
 * @date 2015-12-27 13:37
 */
@Repository("fileResourceDao")
public class FileResourceDaoImpl extends AbstractBaseDao implements FileResourceDao {

    private String TABLE_NAME = "base_fileresource";

    @Override
    public int insert(FileResource fileResource) {
        SqlBuilder sqlBuilder = SqlBuilder.insert(TABLE_NAME
                , "Path,OriginalName,FileSize,BizType,ResType,CloudType,Branch,IsDeleted,CreateTime"
                , ":path,:originalName,:fileSize,:bizType,:resType,:cloudType,:branch,:isDeleted,:createTime");
        ParamsMap paramsMap = ParamsMap.create("path", fileResource.getPath())
                .put("originalName", fileResource.getOriginName())
                .put("fileSize", fileResource.getFileSize())
                .put("bizType", fileResource.getBizType())
                .put("resType", fileResource.getResType())
                .put("cloudType", fileResource.getCloudType())
                .put("branch", fileResource.getBranch())
                .put("isDeleted", false)
                .put("createTime", DateUtils.now());
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlBuilder.toString(),new MapSqlParameterSource(paramsMap.getParams()),keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public FileResource find(int id) {
        SqlBuilder sqlBuilder = fileResourceSqlBuilder().where("Id=:id").deleted(false);
        ParamsMap paramsMap = ParamsMap.create("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), paramsMap.getParams(), new FileResourceMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<FileResource> findFileResources(List<Integer> ids) {
        SqlBuilder sqlBuilder = fileResourceSqlBuilder().where().in("Id", ids).deleted(false);
        return jdbcTemplate.query(sqlBuilder.toString(),new FileResourceMapper());
    }

    private class FileResourceMapper implements RowMapper<FileResource> {
        @Override
        public FileResource mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileResource fileResource = new FileResource();
            fileResource.setId(rs.getInt("Id"));
            fileResource.setPath(rs.getString("Path"));
            fileResource.setBizType(rs.getInt("BizType"));
            fileResource.setOriginName(rs.getString("OriginalName"));
            fileResource.setResType(rs.getInt("ResType"));
            fileResource.setCloudType(rs.getInt("CloudType"));
            fileResource.setBranch(rs.getString("Branch"));
            fileResource.setFileSize(rs.getInt("FileSize"));
            return fileResource;
        }
    }

    private SqlBuilder fileResourceSqlBuilder() {
        SqlBuilder sqlBuilder = SqlBuilder.select(TABLE_NAME, "Id,Path,OriginalName,FileSize,BizType,ResType,CloudType,Branch");
        return sqlBuilder;
    }
}
