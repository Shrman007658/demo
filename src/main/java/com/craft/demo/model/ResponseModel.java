package com.craft.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
public class ResponseModel
{
        List<PlayerData> players;
        String reportUpdatedTimestamp;

}
