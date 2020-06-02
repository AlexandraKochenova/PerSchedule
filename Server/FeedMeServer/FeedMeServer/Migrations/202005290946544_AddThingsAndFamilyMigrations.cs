namespace FeedMeServer.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddThingsAndFamilyMigrations : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.FamilyUserRelations",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        UserFirstID = c.Int(nullable: false),
                        UserSecondID = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.ID);
            
            AddColumn("dbo.Families", "HeadID", c => c.Int(nullable: false));
            AddColumn("dbo.Pets", "FamilyID", c => c.Int(nullable: false));
            AddColumn("dbo.Responsibilities", "ReadyDate", c => c.String());
            AddColumn("dbo.Responsibilities", "Period", c => c.String());
            AddColumn("dbo.Responsibilities", "UserID", c => c.Int(nullable: false));
            AddColumn("dbo.Things", "FamilyID", c => c.Int(nullable: false));
            AddColumn("dbo.Users", "LastName", c => c.String());
            DropColumn("dbo.Families", "Name");
            DropColumn("dbo.Pets", "ResponsibilityList");
            DropColumn("dbo.Pets", "OwnerFamilyId");
            DropColumn("dbo.Responsibilities", "OwnerId");
            DropColumn("dbo.Users", "FamilyName");
            DropColumn("dbo.Users", "IsHeadOfFamily");
            DropTable("dbo.Foods");
        }
        
        public override void Down()
        {
            CreateTable(
                "dbo.Foods",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        CalorieValue = c.Double(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            AddColumn("dbo.Users", "IsHeadOfFamily", c => c.Boolean(nullable: false));
            AddColumn("dbo.Users", "FamilyName", c => c.String());
            AddColumn("dbo.Responsibilities", "OwnerId", c => c.Int(nullable: false));
            AddColumn("dbo.Pets", "OwnerFamilyId", c => c.Int(nullable: false));
            AddColumn("dbo.Pets", "ResponsibilityList", c => c.String());
            AddColumn("dbo.Families", "Name", c => c.String());
            DropColumn("dbo.Users", "LastName");
            DropColumn("dbo.Things", "FamilyID");
            DropColumn("dbo.Responsibilities", "UserID");
            DropColumn("dbo.Responsibilities", "Period");
            DropColumn("dbo.Responsibilities", "ReadyDate");
            DropColumn("dbo.Pets", "FamilyID");
            DropColumn("dbo.Families", "HeadID");
            DropTable("dbo.FamilyUserRelations");
        }
    }
}
