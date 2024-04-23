package com.craft.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;
import java.util.List;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel
{
        List<PlayerData> players;
        String reportUpdatedTimestamp;

}
