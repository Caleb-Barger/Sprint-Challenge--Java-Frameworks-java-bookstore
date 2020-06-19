package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Section;
import com.lambdaschool.foundation.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "sectionService")
public class SectionServiceImpl implements SectionService{
    @Autowired
    SectionRepository sectionrepos;

    @Override
    public Section save(Section section) {
        Section newSection = new Section();

        if (section.getSectionid() != 0) {
            newSection.setSectionid(section.getSectionid());
        }

        newSection.setSectionname(section.getSectionname());

        return sectionrepos.save(newSection);
    }
}
