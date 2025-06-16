import MyAxios from "@/request"
//教务审批
export const getApproval = async (params) => {
    return await MyAxios.request({
        url:'adm/approvalForm/conditionSearch',
        method:"POST",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}
export const getCourse = async (params) => {
    return await MyAxios.request({
        url:'courseManage/codeSearch',
        method:"POST",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}

export const deleteApproval = async (params) => {
    return await MyAxios.request({
        url:'adm/approvalForm/delete',
        method:"DELETE",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}

export const AddApproval = async(params) =>{
    return await MyAxios.request({
        url:'adm/approvalForm/passAdd',
        method:"PUT",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}

export const RejectApproval = async(params) =>{
    return await MyAxios.request({
        url:'adm/approvalForm/reject',
        method:"PUT",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}

export const GetTchInfo = async(params) =>{
    return await MyAxios.request({
        url:'adm/teacherManage/tchIdSearch',
        method:"POST",
        data:params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}