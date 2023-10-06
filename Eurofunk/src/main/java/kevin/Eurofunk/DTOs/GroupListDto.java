package kevin.Eurofunk.DTOs;

import java.util.List;

public class GroupListDto {

    List<Long> groupIdList;

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public GroupListDto(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }
}
