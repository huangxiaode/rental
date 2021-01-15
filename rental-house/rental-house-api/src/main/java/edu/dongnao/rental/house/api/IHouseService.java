package edu.dongnao.rental.house.api;

import edu.dongnao.rental.house.domain.HouseDTO;
import edu.dongnao.rental.house.form.DatatableSearch;
import edu.dongnao.rental.house.form.HouseForm;
import edu.dongnao.rental.lang.ServiceMultiResult;
import edu.dongnao.rental.lang.ServiceResult;

/**
 * 房屋管理服务接口
 * 
 */
public interface IHouseService {
	/**=========================基本操作=========================*/
    /**
     * 新增房源
     * @param houseForm
     * @return
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);
    /**
     * 修改房源信息
     * @param houseForm
     * @return
     */
    ServiceResult<HouseDTO> update(HouseForm houseForm);
    /**
     * 管理后台查询房源信息
     * @param searchBody
     * @param userId
     * @return
     */
    ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody, Long userId);

    /**
     * 查询完整房源信息
     * @param id
     * @return
     */
    ServiceResult<HouseDTO> findCompleteOne(Long id, Long userId);

    /**
     * 移除图片
     * @param id
     * @return
     */
    ServiceResult<Boolean> removePhoto(Long id);

    /**
     * 更新封面
     * @param coverId
     * @param targetId
     * @return
     */
    ServiceResult<Boolean> updateCover(Long coverId, Long targetId);

    /**
     * 新增标签
     * @param houseId
     * @param tag
     * @return
     */
    ServiceResult<Boolean> addTag(Long houseId, String tag);

    /**
     * 移除标签
     * @param houseId
     * @param tag
     * @return
     */
    ServiceResult<Boolean> removeTag(Long houseId, String tag);

    /**
     * 更新房源状态
     * @param id
     * @param status
     * @return
     */
    ServiceResult<Boolean> updateStatus(Long id, int status);
}
